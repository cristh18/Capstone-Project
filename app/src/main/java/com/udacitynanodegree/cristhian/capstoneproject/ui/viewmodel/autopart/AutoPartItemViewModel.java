package com.udacitynanodegree.cristhian.capstoneproject.ui.viewmodel.autopart;

import android.view.View;
import android.widget.Toast;

import com.udacitynanodegree.cristhian.capstoneproject.app.IronHideApplication;
import com.udacitynanodegree.cristhian.capstoneproject.model.AutoPart;
import com.udacitynanodegree.cristhian.capstoneproject.providers.BusRxProvider;
import com.udacitynanodegree.cristhian.capstoneproject.ui.events.ShowAutoPartDetail;

public class AutoPartItemViewModel {

    private AutoPart autoPart;

    public AutoPartItemViewModel(AutoPart autoPart) {
        this.autoPart = autoPart;
    }

    public void selectAutoPart(View view) {
        BusRxProvider.send(new ShowAutoPartDetail(autoPart));
    }

    public AutoPart getAutoPart() {
        return autoPart;
    }
}
