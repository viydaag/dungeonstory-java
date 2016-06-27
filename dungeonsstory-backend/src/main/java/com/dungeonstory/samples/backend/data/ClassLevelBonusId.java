package com.dungeonstory.samples.backend.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ClassLevelBonusId implements Serializable
{
    private Long classId;
    
    private Long levelId;
    
    public ClassLevelBonusId()
    {
        // TODO Auto-generated constructor stub
    }

    public Long getClassId()
    {
        return classId;
    }

    public void setClassId(Long classId)
    {
        this.classId = classId;
    }

    public Long getLevelId()
    {
        return levelId;
    }

    public void setLevelId(Long levelId)
    {
        this.levelId = levelId;
    }

}
