package com.udacitynanodegree.cristhian.capstoneproject.ui.events;

import com.udacitynanodegree.cristhian.capstoneproject.model.AutoPart;

public class ShowAutoPartDetail {

    AutoPart autoPart;

    public ShowAutoPartDetail(AutoPart autoPart) {
        this.autoPart = autoPart;
    }

    public AutoPart getAutoPart() {
        return autoPart;
    }
}
