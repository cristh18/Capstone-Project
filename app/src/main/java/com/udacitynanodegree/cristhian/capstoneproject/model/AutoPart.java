package com.udacitynanodegree.cristhian.capstoneproject.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.app.IronHideApplication;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItem;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class AutoPart implements Parcelable, GenericItem {
    private String name;
    private String fullname;
    private String category;
    private String image;
    private String weight;
    private String description;
    private String style;
    private String partType;

    public AutoPart() {
    }

    public AutoPart(String name, String fullname, String category, String image, String weight, String description, String style, String partType) {
        this.name = name;
        this.fullname = fullname;
        this.category = category;
        this.image = image;
        this.weight = weight;
        this.description = description;
        this.style = style;
        this.partType = partType;
    }

    public String getName() {
        if (name == null) {
            name = "";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullname() {
        if (fullname == null) {
            fullname = "";
        }
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCategory() {
        if (category == null) {
            category = IronHideApplication.getApp().getString(R.string.copy_does_not_apply);
        }
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWeight() {
        if (weight == null) {
            weight = IronHideApplication.getApp().getString(R.string.copy_does_not_apply);
        }
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDescription() {
        if (description == null) {
            description = IronHideApplication.getApp().getString(R.string.copy_does_not_apply);
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStyle() {
        if (style == null) {
            style = IronHideApplication.getApp().getString(R.string.copy_does_not_apply);
        }
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getPartType() {
        if (partType == null) {
            partType = IronHideApplication.getApp().getString(R.string.copy_does_not_apply);
        }
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    @Override
    public String toString() {
        return "AutoPart{" +
                "name='" + name + '\'' +
                ", fullname='" + fullname + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                ", weight='" + weight + '\'' +
                ", description='" + description + '\'' +
                ", style='" + style + '\'' +
                ", type='" + partType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AutoPart autoPart = (AutoPart) o;

        if (name != null ? !name.equals(autoPart.name) : autoPart.name != null) return false;
        if (fullname != null ? !fullname.equals(autoPart.fullname) : autoPart.fullname != null)
            return false;
        if (category != null ? !category.equals(autoPart.category) : autoPart.category != null)
            return false;
        if (image != null ? !image.equals(autoPart.image) : autoPart.image != null) return false;
        if (weight != null ? !weight.equals(autoPart.weight) : autoPart.weight != null)
            return false;
        if (description != null ? !description.equals(autoPart.description) : autoPart.description != null)
            return false;
        if (style != null ? !style.equals(autoPart.style) : autoPart.style != null) return false;
        return partType != null ? partType.equals(autoPart.partType) : autoPart.partType == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (fullname != null ? fullname.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (style != null ? style.hashCode() : 0);
        result = 31 * result + (partType != null ? partType.hashCode() : 0);
        return result;
    }

    public static final Parcelable.Creator<AutoPart> CREATOR = new Creator<AutoPart>() {

        public AutoPart createFromParcel(Parcel source) {
            return new AutoPart(
                    source.readString(),
                    source.readString(),
                    source.readString(),
                    source.readString(),
                    source.readString(),
                    source.readString(),
                    source.readString(),
                    source.readString());
        }

        public AutoPart[] newArray(int size) {
            return new AutoPart[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(fullname);
        dest.writeString(category);
        dest.writeString(image);
        dest.writeString(weight);
        dest.writeString(description);
        dest.writeString(style);
        dest.writeString(partType);
    }

    @Override
    public int getType() {
        return 0;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("fullname", fullname);
        result.put("category", category);
        result.put("image", image);
        result.put("weight", weight);
        result.put("description", description);
        result.put("style", style);
        result.put("partType", partType);
        return result;
    }
}
