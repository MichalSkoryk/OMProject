package com.skoryk.superapp.activityType;

import com.skoryk.superapp.model.ActivityType;
import com.skoryk.superapp.repository.ActivityTypeRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActivityTypeService {
    private final ActivityTypeRepository activityTypeRepository;

    public Optional<ActivityType> findById(UUID activityTypeId) {
        return activityTypeRepository.findById(activityTypeId);
    }

    public ArrayList<ActivityType> findAllByGroupId(UUID groupId){
        return activityTypeRepository.findByGroupId(groupId);
    }

    public ActivityType save(ActivityTypeCreateRequest activityTypeCreateRequest) {


        ActivityType activityType = ActivityType
                .builder()
                .groupId(activityTypeCreateRequest.getGroupId())
                .activityName(activityTypeCreateRequest.getActivityName())
                .state(true)
                .build();

        return activityTypeRepository.save(activityType);
    }

    @Getter
    static class ActivityTypeUpdateDetermine {
        private final ActivityType activity;
        private boolean updated = false;

        protected ActivityTypeUpdateDetermine(ActivityTypePatchRequest newActivity, ActivityType oldActivity) {

            if (oldActivity.getActivityName().equals(newActivity.getActivityName())) {
                oldActivity.setActivityName(newActivity.getActivityName());
                this.updated = true;
            }
            if (oldActivity.isState() != newActivity.isState()) {
                oldActivity.setState(newActivity.isState());
                this.updated = true;
            }

            this.activity = oldActivity;
        }
    }

    public ActivityType patch(ActivityTypePatchRequest activityTypePatchRequest, UUID activityTypeId) {

        ActivityType oldActivityType = findById(activityTypeId).orElse(null);
        if (oldActivityType != null){
            ActivityTypeUpdateDetermine activityTypeUpdateDetermine = new ActivityTypeUpdateDetermine(activityTypePatchRequest, oldActivityType);
            if(activityTypeUpdateDetermine.updated)
                return activityTypeRepository.save(activityTypeUpdateDetermine.activity);
            return activityTypeUpdateDetermine.activity;
        }
        return null;
    }
}
