package com.final_test.moneylovely.event;

import com.final_test.moneylovely.model.Thu_Chi_Model;

public interface EventClickDetail {
    void onClickEditCv(int i, Thu_Chi_Model model);
    void onClickDeleteCv(int i,Thu_Chi_Model model);
    void onClickItemCv(int i,Thu_Chi_Model model);
}
