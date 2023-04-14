package com.example.CounselingTree.payload;

import com.example.CounselingTree.entities.Employee;

public class CounsellorAndCounsellee {
    private long counsellorId;
    private long counselleeId;

    public CounsellorAndCounsellee(long counsellorId, long counselleeId) {
        this.counsellorId = counsellorId;
        this.counselleeId = counselleeId;
    }
    public long getCounsellorId() {
        return counsellorId;
    }
    public long getCounselleeId() {
        return counselleeId;
    }
}
