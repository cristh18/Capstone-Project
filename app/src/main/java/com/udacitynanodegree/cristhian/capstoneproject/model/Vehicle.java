package com.udacitynanodegree.cristhian.capstoneproject.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@IgnoreExtraProperties
public class Vehicle implements GenericItem, Parcelable {

    private String year;
    private String make;
    private String model;
    private String submodel;
    private String engine;
    private String description;
    private String image;
    private List<AutoPart> autoParts;
    private String url;

    public Vehicle() {
    }

    public Vehicle(String year, String make, String model, String submodel, String engine, String description, String image, List<AutoPart> autoParts, String url) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.submodel = submodel;
        this.engine = engine;
        this.description = description;
        this.image = image;
        this.autoParts = autoParts;
        this.url = url;
    }

    public String getYear() {
        if (year == null) {
            year = "";
        }
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMake() {
        if (make == null) {
            make = "";
        }
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        if (model == null) {
            model = "";
        }
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSubmodel() {
        if (submodel == null) {
            submodel = "";
        }
        return submodel;
    }

    public void setSubmodel(String submodel) {
        this.submodel = submodel;
    }

    public String getEngine() {
        if (engine == null) {
            engine = "";
        }
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getDescription() {
        if (description == null) {
            description = "";
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        if (image == null) {
            image = "";
        }
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<AutoPart> getAutoParts() {
        if (autoParts == null) {
            autoParts = new ArrayList<>();
        }
        return autoParts;
    }

    public void setAutoParts(List<AutoPart> autoParts) {
        this.autoParts = autoParts;
    }

    public String getUrl() {
        if (url == null) {
            url = "";
        }
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return getMake().concat(" ").concat(getModel()).concat(" ").concat(getSubmodel()).concat(" ").concat(getYear());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        if (year != null ? !year.equals(vehicle.year) : vehicle.year != null) return false;
        if (make != null ? !make.equals(vehicle.make) : vehicle.make != null) return false;
        if (model != null ? !model.equals(vehicle.model) : vehicle.model != null) return false;
        if (submodel != null ? !submodel.equals(vehicle.submodel) : vehicle.submodel != null)
            return false;
        if (engine != null ? !engine.equals(vehicle.engine) : vehicle.engine != null) return false;
        if (description != null ? !description.equals(vehicle.description) : vehicle.description != null)
            return false;
        if (image != null ? !image.equals(vehicle.image) : vehicle.image != null) return false;
        if (autoParts != null ? !autoParts.equals(vehicle.autoParts) : vehicle.autoParts != null)
            return false;
        return url != null ? url.equals(vehicle.url) : vehicle.url == null;

    }

    @Override
    public int hashCode() {
        int result = year != null ? year.hashCode() : 0;
        result = 31 * result + (make != null ? make.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (submodel != null ? submodel.hashCode() : 0);
        result = 31 * result + (engine != null ? engine.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (autoParts != null ? autoParts.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @Override
    public int getType() {
        return 0;
    }

    public static final Parcelable.Creator<Vehicle> CREATOR = new Creator<Vehicle>() {

        public Vehicle createFromParcel(Parcel source) {
            return new Vehicle(
                    source.readString(),
                    source.readString(),
                    source.readString(),
                    source.readString(),
                    source.readString(),
                    source.readString(),
                    source.readString(),
                    source.readArrayList(Vehicle.class.getClassLoader()),
                    source.readString());
        }

        public Vehicle[] newArray(int size) {
            return new Vehicle[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(year);
        dest.writeString(make);
        dest.writeString(model);
        dest.writeString(submodel);
        dest.writeString(engine);
        dest.writeString(description);
        dest.writeString(image);
        dest.writeList(autoParts);
        dest.writeString(url);
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("year", year);
        result.put("make", make);
        result.put("model", model);
        result.put("submodel", submodel);
        result.put("engine", engine);
        result.put("description", description);
        result.put("image", image);
        result.put("autoParts", autoParts);
        result.put("url", url);
        return result;
    }
}
