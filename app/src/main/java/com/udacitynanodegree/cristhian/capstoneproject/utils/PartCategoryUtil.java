package com.udacitynanodegree.cristhian.capstoneproject.utils;

import android.content.Context;

import com.udacitynanodegree.cristhian.capstoneproject.model.AutoPart;

import java.util.HashSet;
import java.util.List;

public class PartCategoryUtil {

    public static HashSet<String> getCategories(List<AutoPart> autoParts) {
        HashSet<String> categories = new HashSet<>();
        if (autoParts != null && !autoParts.isEmpty()) {
            for (AutoPart autoPart : autoParts) {
                categories.add(autoPart.getCategory());
            }
        }
        return categories;
    }
}
