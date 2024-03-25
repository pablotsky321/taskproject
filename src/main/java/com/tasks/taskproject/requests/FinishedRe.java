package com.tasks.taskproject.requests;

public class FinishedRe {

    private boolean finished;

    public FinishedRe(boolean finished) {
        this.finished = finished;
    }

    public FinishedRe() {

    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

}
