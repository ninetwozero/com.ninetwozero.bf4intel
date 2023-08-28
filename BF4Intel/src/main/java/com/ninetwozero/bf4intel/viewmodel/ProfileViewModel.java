package com.ninetwozero.bf4intel.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.database.entities.ProfileEntity;
import com.ninetwozero.bf4intel.repository.ProfileRepository;

public class ProfileViewModel extends AndroidViewModel {

    private ProfileRepository repo;
    //private final LiveData<List<ProfileEntity>> profileEntity;

    public ProfileViewModel (@NonNull Application application, ProfileRepository repository) {
        super(application);
        repo = repository;
    }

    public void insert(ProfileEntity entity) {
        repo.insert(entity);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {


        @NonNull
        private final Application app;
        private final ProfileRepository repository;
        public Factory(@NonNull Application application) {
            app = application;
            repository = ((Bf4Intel) application).getProfileRepository();
        }

        @SuppressWarnings("unchecked")
        @Override
        @NonNull
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ProfileViewModel(app, repository);
        }
    }
}
