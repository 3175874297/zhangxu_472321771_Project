package com.auxiliary;

import java.util.Set;

public class GroupMapper {
    public int groupID;
    public Set<Integer> stateSet;

    public GroupMapper(int groupID, Set<Integer> stateSet) {
        this.groupID = groupID;
        this.stateSet = stateSet;
    }
}
