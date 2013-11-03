package com.ninetwozero.bf4intel.assignments;

public class Assignment {
    private int completion;
    private boolean isTracking;
    private int image;
    private AssignmentPrerequirement prerequirement;

    public Assignment(int completion, boolean isTracking, int image, AssignmentPrerequirement prerequirement) {
        this.completion = completion;
        this.isTracking = isTracking;
        this.image = image;
        this.prerequirement = prerequirement;
    }

    public int getCompletion() {
        return completion;
    }

    public boolean isTracking() {
        return isTracking;
    }

    public int getImage() {
        return image;
    }

    public AssignmentPrerequirement getPrerequirement() {
        return prerequirement;
    }
}
