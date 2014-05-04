package com.ninetwozero.bf4intel.ui.assignments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseDialogFragment;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.assignments.Assignment;
import com.ninetwozero.bf4intel.json.assignments.AssignmentAward;
import com.ninetwozero.bf4intel.json.assignments.AssignmentCriteria;
import com.ninetwozero.bf4intel.json.assignments.AssignmentCriteriaContainer;
import com.ninetwozero.bf4intel.json.assignments.AssignmentPrerequisite;
import com.ninetwozero.bf4intel.json.assignments.AssignmentReward;
import com.ninetwozero.bf4intel.resources.maps.assignments.AssignmentImageMap;
import com.ninetwozero.bf4intel.resources.maps.assignments.AssignmentRequirementStringMap;
import com.ninetwozero.bf4intel.resources.maps.assignments.AssignmentStringMap;
import com.ninetwozero.bf4intel.resources.maps.assignments.ExpansionIconsImageMap;
import com.ninetwozero.bf4intel.resources.maps.awards.AwardStringMap;
import com.ninetwozero.bf4intel.resources.maps.camoflagues.CamoImageMap;
import com.ninetwozero.bf4intel.resources.maps.camoflagues.CamoStringMap;
import com.ninetwozero.bf4intel.resources.maps.dogtags.DogtagStringMap;
import com.ninetwozero.bf4intel.resources.maps.unlocks.VehicleUnlockImageMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehicleImageMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehicleStringMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehicleUnlockStringMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponStringMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponsImageMap;
import com.squareup.picasso.Picasso;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class AssignmentDetailFragment extends BaseDialogFragment {
    public static final String INTENT_ASSIGNMENT = "assignment";
    public static final String TAG = "AssignmentDetailFragment";

    private Assignment assignment;

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
        populateViews(view);
    }

    private void setupData(final Bundle data) {
        assignment = (Assignment) data.getSerializable(INTENT_ASSIGNMENT);
    }

    private void populateViews(final View view) {
        final AssignmentAward award = assignment.getAward();
        populateOverviewBox(view, award);
        populatePreRequisites(view);
        populateAwardRequirements(view);
        populateRewardBox(view);
    }

    private void populateOverviewBox(final View view, final AssignmentAward award) {
        setText(view, R.id.assignment_title, AssignmentStringMap.get(award.getUniqueName()));
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
        imageView.setAlpha(completed ? 1f : 0.5f);
        Picasso.with(getActivity()).load(AssignmentImageMap.get(award.getUniqueName())).into(imageView);
    }

    private void populatePreRequisites(final View view) {
        final View cardWrapperView = view.findViewById(R.id.wrap_assignment_prereq);
        if (assignment.getPrerequisites().size() > 0) {
            final ViewGroup containerView = (ViewGroup) cardWrapperView.findViewById(R.id.assignment_prereq_container);
            for (AssignmentPrerequisite prerequisite : assignment.getPrerequisites()) {
                final View tempView = layoutInflater.inflate(R.layout.list_item_assignment_prereq, containerView, false);
                final AssignmentPrerequisite.Type groupType = AssignmentPrerequisite.Type.from(prerequisite.getGroup());

                setImage(tempView, R.id.image_prereq_icon, fetchPrerequisiteImage(groupType));
                setText(tempView, R.id.assignment_prerequisite, getPrerequisiteString(prerequisite.getKey(), groupType));
                setVisibility(
                    tempView,
                    R.id.assignment_prereq_completed,
                    prerequisite.getTimesTaken() > 0 ? View.VISIBLE : View.GONE
                );

                containerView.addView(tempView);
            }
        } else {
            cardWrapperView.setVisibility(View.GONE);
        }
    }

    private int getPrerequisiteString(String key, AssignmentPrerequisite.Type groupType) {
        if (groupType == AssignmentPrerequisite.Type.MISSION) {
            return AssignmentStringMap.get(key);
        }
        return AwardStringMap.get(key);
    }

    private void populateAwardRequirements(final View view) {
        final View cardWrapperView = view.findViewById(R.id.wrap_assignment_tasks);
        if (assignment.getCriterias().size() > 0) {
            for (AssignmentCriteriaContainer criteriaContainer : assignment.getCriterias()) {
                final ViewGroup containerView = (ViewGroup) cardWrapperView.findViewById(R.id.assignment_tasks_container);
                final View tempView = layoutInflater.inflate(R.layout.list_item_assignment_task, containerView, false);
                final AssignmentCriteria criteria = criteriaContainer.getCriteria();

                setText(tempView, R.id.task_label, AssignmentRequirementStringMap.get(criteria.getKey()));
                if (criteriaContainer.isCompleted()) {
                    setVisibility(tempView, R.id.task_completion, View.GONE);
                    setVisibility(tempView, R.id.task_completed_icon, View.VISIBLE);
                } else {
                    setVisibility(tempView, R.id.task_completion, View.VISIBLE);
                    setVisibility(tempView, R.id.task_completed_icon, View.GONE);
                    setText(tempView, R.id.task_completion, getTaskCompletionString(criteriaContainer, criteria));
                }
                containerView.addView(tempView);
            }
        } else {
            cardWrapperView.setVisibility(View.GONE);
        }
    }

    private String getTaskCompletionString(
        final AssignmentCriteriaContainer criteriaContainer, final AssignmentCriteria criteria
    ) {
        return String.format(
            Locale.getDefault(),
            getString(R.string.generic_x_of_y),
            criteriaContainer.getCurrentValue(),
            criteria.getUnlockThreshold()
        );
    }

    private void populateRewardBox(final View view) {
        final Set<String> usedNames = new HashSet<String>();
        for (AssignmentReward reward : assignment.getRewards()) {
            if (usedNames.contains(reward.getName())) {
                continue;
            }

            final ViewGroup containerView = (ViewGroup) view.findViewById(R.id.assignment_reward_container);
            final View tempView = layoutInflater.inflate(R.layout.list_item_assignment_reward, containerView, false);

            setText(tempView, R.id.reward_title, fetchRewardTitle(reward));
            setImageForReward(tempView, R.id.reward_image, reward);

            containerView.addView(tempView);
            usedNames.add(reward.getName());
        }
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
                imageResource = WeaponsImageMap.get(reward.getName());
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
            .load(UrlFactory.buildDogtagImageURL(reward.getImageIndex()))
            .placeholder(R.drawable.dogtag_plain)
            .into((ImageView) view.findViewById(viewId));
    }

    private void showExpansionPackIcon(final View view, final AssignmentAward award) {
        final ImageView expansionIcon = (ImageView) view.findViewById(R.id.expansion_icon);
        expansionIcon.setVisibility(View.VISIBLE);
        Picasso.with(getActivity()).load(ExpansionIconsImageMap.get(award.getExpansionPack())).into(expansionIcon);
    }

    private void populateViewForTracking(final View view, final Assignment assignment) {
        final ImageView imagePrerequisite = (ImageView) view.findViewById(R.id.assignment_prerequisite);
        //imagePrerequisite.setImageResource(fetchPrerequisiteImage(assignment.getDependencyGroup()));
        imagePrerequisite.setVisibility(View.INVISIBLE);

        final ProgressBar completionProgress = (ProgressBar) view.findViewById(R.id.assignment_completion);
        completionProgress.setMax(100);
        completionProgress.setProgress(assignment.getCompletion());
        completionProgress.setVisibility(View.VISIBLE);
    }

    private int fetchPrerequisiteImage(final AssignmentPrerequisite.Type group) {
        switch (group) {
            case RANK:
                return R.drawable.ic_stat_rank;
            case MISSION:
                return R.drawable.ic_stat_award;
            default:
                return R.drawable.empty;
        }
    }
}
