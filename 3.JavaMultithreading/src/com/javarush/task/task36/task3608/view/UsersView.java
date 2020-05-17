package com.javarush.task.task36.task3608.view;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.controller.Controller;
import com.javarush.task.task36.task3608.model.ModelData;

public class UsersView implements View {
    private Controller controller = new Controller();

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }


    @Override
    public void refresh(ModelData modelData) {
        if (modelData.isDisplayDeletedUserList(true)) {
            System.out.println("All deleted users:");
        }else {
        System.out.println("All users:");
        }
        for (User user : modelData.getUsers()) {
            System.out.println("\t"+ user);
        }
        System.out.println("===================================================");
    }


    public void fireEventShowAllUsers() {
        controller.onShowAllUsers();
    }

    public void fireEventShowDeletedUsers() {
        controller.onShowAllDeletedUsers();
    }

    public void fireEventOpenUserEditForm(long id) {
        controller.onOpenUserEditForm(id);
    }

}




