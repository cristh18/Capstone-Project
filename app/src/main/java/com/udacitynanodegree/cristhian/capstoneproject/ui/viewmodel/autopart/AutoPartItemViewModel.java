package com.udacitynanodegree.cristhian.capstoneproject.ui.viewmodel.autopart;

import android.view.View;
import android.widget.Toast;

import com.udacitynanodegree.cristhian.capstoneproject.app.IronHideApplication;
import com.udacitynanodegree.cristhian.capstoneproject.model.AutoPart;

public class AutoPartItemViewModel {

    private AutoPartListener autoPartListener;
    private AutoPart autoPart;

    public AutoPartItemViewModel(AutoPart autoPart) {
        this.autoPart = autoPart;
    }

    public void selectAutoPart(View view){
        Toast.makeText(IronHideApplication.getApp(), "Show detail of ".concat(autoPart.getName()).concat("..."), Toast.LENGTH_LONG).show();
    }

    public AutoPart getAutoPart() {
        return autoPart;
    }

    public void setAutoPartListener(AutoPartListener autoPartListener) {
        this.autoPartListener = autoPartListener;
    }

    public interface AutoPartListener{
        void onAutoPartSelected(AutoPart autoPart);
    }
}
