package com.ninetwozero.bf4intel.ui.assignments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseDialogFragment;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.UnlockType;
import com.ninetwozero.bf4intel.json.assignments.Assignment;
import com.ninetwozero.bf4intel.json.assignments.AssignmentAward;
import com.ninetwozero.bf4intel.json.assignments.AssignmentCriteria;
import com.ninetwozero.bf4intel.json.assignments.AssignmentPrerequisite;
import com.ninetwozero.bf4intel.json.assignments.AssignmentReward;
import com.ninetwozero.bf4intel.resources.maps.assignments.AssignmentCriteriaStringMap;
import com.ninetwozero.bf4intel.resources.maps.assignments.AssignmentImageMap;
import com.ninetwozero.bf4intel.resources.maps.assignments.AssignmentStringMap;
import com.ninetwozero.bf4intel.resources.maps.assignments.ExpansionIconsImageMap;
import com.ninetwozero.bf4intel.resources.maps.camoflagues.CamoImageMap;
import com.ninetwozero.bf4intel.resources.maps.camoflagues.CamoStringMap;
import com.ninetwozero.bf4intel.resources.maps.dogtags.DogtagStringMap;
import com.ninetwozero.bf4intel.resources.maps.unlocks.VehicleUnlockImageMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehicleImageMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehicleStringMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponImageMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponStringMap;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class AssignmentDetailFragment extends BaseDialogFragment {
    public static final String INTENT_ASSIGNMENT = "assignment";
    public static final String INTENT_USER_HAS_EXPANSION = "expansions";
    public static final String TAG = "AssignmentDetailFragment";

    private static final String KEY_FIRESTARTER = "WARSAW_ID_P_XP0_AS_01";
    private static final String KEY_PHANTOMINITIATE = "WARSAW_ID_P_XP3_AWARD_GHOST";

    private Assignment assignment;
    private AssignmentAward assignmentAward;
    private boolean userHasExpansionPack;

    public static AssignmentDetailFragment newInstance(final Bundle data) {
        final AssignmentDetailFragment fragment = new AssignmentDetailFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View view = inflater.inflate(R.layout.fragment_assignment_details, container, false);
        initialize(view);
        return view;
    }

    private void initialize(final View view) {
        setupData(getArguments());
        setupTitle();
        populateViews(view, assignmentAward);
    }

    private void setupData(final Bundle data) {
        assignment = (Assignment) data.getSerializable(INTENT_ASSIGNMENT);
        userHasExpansionPack = data.getBoolean(INTENT_USER_HAS_EXPANSION);
        assignmentAward = assignment.getAward();
    }

    private void setupTitle() {
        final String key = assignmentAward.getUniqueName();
        setTitle(getString(AssignmentStringMap.get(key)));
    }

    private void populateViews(final View view, final AssignmentAward award) {
        populateOverviewBox(view, award);
        populatePreRequisites(view);
        populateAwardRequirements(view);
        populateRewardBox(view);
    }

    private void populateOverviewBox(final View view, final AssignmentAward award) {
        showAssignmentImage(view, award, assignment.isCompleted());
        setProgress(view, R.id.assignment_completion, assignment.getCompletion());
        setText(view, R.id.assignment_completion_text, assignment.getCompletion() + "%");
        setVisibility(
            view,
            R.id.wrap_assignment_completion,
            assignment.isCompleted() ? View.GONE : View.VISIBLE
        );
    }

    private void showAssignmentImage(final View view, final AssignmentAward award, final boolean completed) {
        final ImageView imageView = (ImageView) view.findViewById(R.id.assignment_image);
        imageView.setAlpha(completed ? ALPHA_ENABLED : ALPHA_DISABLED);
        Picasso.with(getActivity()).load(AssignmentImageMap.get(award.getUniqueName())).into(imageView);
    }

    private void populatePreRequisites(final View view) {
        final View cardWrapperView = view.findViewById(R.id.wrap_assignment_prereq);
        if (assignment.getPrerequisites().size() > 0) {
            final ViewGroup containerView = (ViewGroup) cardWrapperView.findViewById(R.id.assignment_prereq_container);
            containerView.removeAllViews();

            for (AssignmentPrerequisite prerequisite : assignment.getPrerequisites()) {
                final View tempView = layoutInflater.inflate(R.layout.list_item_assignment_prereq, containerView, false);
                containerView.addView(populatePreRequisite(tempView, prerequisite));
            }

            if (assignmentAward.hasExpansionPack()) {
                final View tempView = layoutInflater.inflate(R.layout.list_item_assignment_prereq, containerView, false);
                containerView.addView(populatePreRequisite(tempView, fetchPrerequisiteForExpansion()));
            }
        } else {
            cardWrapperView.setVisibility(View.GONE);
        }
    }

    private View populatePreRequisite(final View view, final AssignmentPrerequisite prerequisite) {
        final AssignmentPrerequisite.Type groupType = AssignmentPrerequisite.Type.from(prerequisite.getGroup());

        setImage(view, R.id.image_prereq_icon, fetchPrerequisiteImage(groupType));
        setText(view, R.id.assignment_prerequisite, fetchPrerequisiteTitle(prerequisite.getKey(), groupType));
        setVisibility(
            view,
            R.id.assignment_prereq_completed,
            prerequisite.getTimesTaken() > 0 ? View.VISIBLE : View.GONE
        );

        return view;
    }

    private AssignmentPrerequisite fetchPrerequisiteForExpansion() {
        final String pack = assignmentAward.getExpansionPack().toUpperCase(Locale.getDefault());
        return new AssignmentPrerequisite(
            "WARSAW_ID_P_AWARD_PREREQUISITE_" + pack,
            assignmentAward.getExpansionPack(),
            AssignmentPrerequisite.Type.EXPANSION.getGroup(),
            userHasExpansionPack ? 1 : 0
        );
    }

    private int fetchPrerequisiteTitle(String key, AssignmentPrerequisite.Type groupType) {
        if (groupType == AssignmentPrerequisite.Type.MISSION) {
            return AssignmentStringMap.get(key);
        } else {
            return AssignmentCriteriaStringMap.get(key);
        }
    }

    private void populateAwardRequirements(final View view) {
        final View cardWrapperView = view.findViewById(R.id.wrap_assignment_tasks);
        List<AssignmentCriteria> criterias = assignment.getCriterias();

        if (assignmentAward.getCode().startsWith("SP_")) {
            criterias = fetchCriteriaForSinglePlayer();
        }

        if (criterias.size() > 0) {
            final ViewGroup containerView = (ViewGroup) cardWrapperView.findViewById(R.id.assignment_tasks_container);
            containerView.removeAllViews();

            for (AssignmentCriteria criteria : criterias) {
                final View tempView = layoutInflater.inflate(R.layout.list_item_assignment_task, containerView, false);
                final boolean isInRoundRequirement = "CriteriaType_IAR_InARound".equals(criteria.getCriteriaType());

                setText(tempView, R.id.task_label, AssignmentCriteriaStringMap.get(criteria.getKey()));
                setText(tempView, R.id.task_completion, getTaskCompletionString(criteria));
                setVisibility(tempView, R.id.task_round, isInRoundRequirement ? View.VISIBLE : View.GONE);

                containerView.addView(tempView);
            }
        } else {
            cardWrapperView.setVisibility(View.GONE);
        }
    }

    private List<AssignmentCriteria> fetchCriteriaForSinglePlayer() {
        final String name = buildCriteriaStringKey(assignmentAward.getUniqueName());
        final int completion = assignment.getCompletion();
        final String criteriaType = "CriteriaType_GLOBAL_AllTimeTotal";

        final List<AssignmentCriteria> criterias = new ArrayList<AssignmentCriteria>();
        criterias.add(new AssignmentCriteria(name, completion / 100, 1, completion, criteriaType));
        return criterias;
    }

    private String buildCriteriaStringKey(String key) {
        final String number = key.substring(key.lastIndexOf("_") + 1);
        return "WARSAW_ID_P_SP_AWARD_ASSGN" + number + "_CR1";
    }

    private String getTaskCompletionString(final AssignmentCriteria criteria) {
        return String.format(
            getString(R.string.generic_x_of_y),
            criteria.getCurrentValue(),
            criteria.getUnlockThreshold()
        );
    }

    private void populateRewardBox(final View view) {
        final Set<String> usedNames = new HashSet<String>();

        final View cardWrapperView = view.findViewById(R.id.wrap_assignment_rewards);
        List<AssignmentReward> rewards = assignment.getRewards();

        if (assignmentAward.getUniqueName().equals(KEY_FIRESTARTER)) {
            rewards = fetchRewardsForFirestarter();
        }

        if (assignmentAward.getUniqueName().equals(KEY_PHANTOMINITIATE)) {
            rewards = fetchRewardsForPhantomInitiate();
        }

        if (rewards.size() > 0) {
            final ViewGroup containerView = (ViewGroup) view.findViewById(R.id.assignment_reward_container);
            containerView.removeAllViews();

            for (AssignmentReward reward : rewards) {
                if (usedNames.contains(reward.getName())) {
                    continue;
                }

                final View tempView = layoutInflater.inflate(R.layout.list_item_assignment_reward, containerView, false);

                setText(tempView, R.id.reward_title, fetchRewardTitle(reward));
                setImageForReward(tempView, R.id.reward_image, reward);

                usedNames.add(reward.getName());
                containerView.addView(tempView);
            }
        } else {
            cardWrapperView.setVisibility(View.GONE);
        }
    }

    // Fixes issue with the Firestarter assignment as it returns 12 rewards, but only 3 are needed
    private List<AssignmentReward> fetchRewardsForFirestarter() {
        final List<AssignmentReward> rewards = new ArrayList<AssignmentReward>();
        final String[] fakeNames = new String[]{
            "WARSAW_ID_P_XP0_CAMO_NAME_FIRESTARTER1",
            "WARSAW_ID_P_XP0_CAMO_NAME_FIRESTARTER2",
            "WARSAW_ID_P_XP0_CAMO_NAME_FIRESTARTER3"
        };

        for (String name : fakeNames) {
            rewards.add(new AssignmentReward(name, null, UnlockType.APPEARANCE));
        }

        return rewards;
    }

    /*Phantom Initiate hardcoded assignments same as firestarter*/
    private List<AssignmentReward> fetchRewardsForPhantomInitiate() {
        final List<AssignmentReward> rewards = new ArrayList<AssignmentReward>();
        final String[] fakeNames = new String[]{
            "WARSAW_ID_P_XP0_CAMO_NAME_PHANTOMINITIATE1",
            "WARSAW_ID_P_XP0_CAMO_NAME_PHANTOMINITIATE2",
            "WARSAW_ID_P_XP0_CAMO_NAME_PHANTOMINITIATE3"
        };

        for (String name : fakeNames) {
            rewards.add(new AssignmentReward(name, null, UnlockType.APPEARANCE));
        }

        return rewards;
    }

    private int fetchRewardTitle(AssignmentReward reward) {
        switch (reward.getUnlockType()) {
            case WEAPON:
                return WeaponStringMap.get(reward.getName());
            case VEHICLE_ADDON:
                return VehicleStringMap.get(reward.getName());
            case APPEARANCE:
                return CamoStringMap.get(reward.getName());
            case DOGTAG:
                return DogtagStringMap.get(reward.getName());
            case KIT_ITEM:
                return VehicleStringMap.get(reward.getName());
            default:
                return R.string.na;
        }
    }

    private void setImageForReward(View view, int viewId, AssignmentReward reward) {
        int imageResource = 0;
        switch (reward.getUnlockType()) {
            case WEAPON:
                imageResource = WeaponImageMap.get(reward.getName());
                break;
            case VEHICLE_ADDON:
                imageResource = VehicleUnlockImageMap.get(reward.getName());
                break;
            case APPEARANCE:
                imageResource = CamoImageMap.get(reward.getName());
                break;
            case KIT_ITEM:
                imageResource = VehicleImageMap.get(reward.getName());
                break;
            case DOGTAG:
                setImageForDogtagReward(view, viewId, reward);
                break;
            default:
                imageResource = R.drawable.kit_none;
                break;
        }

        if (imageResource > 0) {
            setImage(view, viewId, imageResource);
        }
    }

    private void setImageForDogtagReward(View view, int viewId, AssignmentReward reward) {
        Picasso.with(getActivity())
            .load(UrlFactory.buildDogtagImageURL(reward.getImageIndex(), reward.isAdvanced()))
            .placeholder(R.drawable.dogtag_plain)
            .into((ImageView) view.findViewById(viewId));
    }

    private int fetchPrerequisiteImage(final AssignmentPrerequisite.Type group) {
        switch (group) {
            case RANK:
                return R.drawable.ic_statistics_rank;
            case MISSION:
                return R.drawable.ic_statistics_award;
            case EXPANSION:
                return fetchExpansionImage();
            default:
                return R.drawable.empty;
        }
    }

    private int fetchExpansionImage() {
        return ExpansionIconsImageMap.get(assignmentAward.getExpansionPack());
    }
}
