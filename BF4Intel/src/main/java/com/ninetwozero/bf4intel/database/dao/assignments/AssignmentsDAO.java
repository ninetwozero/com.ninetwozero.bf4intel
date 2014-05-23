package com.ninetwozero.bf4intel.database.dao.assignments;

import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.json.assignments.SortedAssignmentContainer;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.PrimaryKey;
import se.emilsjolander.sprinkles.annotations.Table;

@Table("SoldierAssignments")
public class AssignmentsDAO extends Model {
    public static final String TABLE_NAME = "SoldierAssignments";

    @PrimaryKey
    @Column("soldierId")
    private String soldierId;

    @Column("soldierName")
    private String soldierName;

    @PrimaryKey
    @Column("platformId")
    private int platformId;

    @Column("json")
    private SortedAssignmentContainer sortedAssignmentContainer;

    @Column("version")
    private int version;

    public AssignmentsDAO() {}

    public AssignmentsDAO(String soldierId, String soldierName, int platformId, SortedAssignmentContainer sortedAssignmentContainer) {
        this.soldierId = soldierId;
        this.soldierName = soldierName;
        this.platformId = platformId;
        this.sortedAssignmentContainer = sortedAssignmentContainer;
        this.version = BuildConfig.VERSION_CODE;
    }

    public String getSoldierId() {
        return soldierId;
    }

    public String getSoldierName() {
        return soldierName;
    }

    public int getPlatformId() {
        return platformId;
    }

    public SortedAssignmentContainer getSortedAssignmentContainer() {
        return sortedAssignmentContainer;
    }
}
