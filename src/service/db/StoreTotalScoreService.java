package service.db;

import dao.TotalScoreDao;

public class StoreTotalScoreService {
    private String name;
    private int score;

    public StoreTotalScoreService(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public void storeTotalScore(){
        TotalScoreDao totalScoreDao = new TotalScoreDao(name,score);
        totalScoreDao.insert();
    }
}
