package com.you.online_exam.dao;

import lombok.Builder;
import lombok.Data;

/**
 * ScoreDao
 *
 * @author 尤广富 (Online Class)
 * @create 2019-03-28 17:40
 **/
@Data
@Builder
public class ScoreDao {

    private String id;

    private String score;

    public ScoreDao(String id, String score) {
        this.id = id;
        this.score = score;
    }

    public ScoreDao() {
    }
}
