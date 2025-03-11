package com.skoryk.superapp.proposition;

import com.skoryk.superapp.model.Proposition;
import com.skoryk.superapp.repository.PropositionRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PropositionService {

    private final PropositionRepository propositionRepository;

    public Optional<Proposition> getPropositionById(UUID propositionId) {
        return propositionRepository.findById(propositionId);
    }

    public ArrayList<Proposition> getPropositionsFromGroupByIdActivity(UUID groupId, UUID activityTypeId) {
        return propositionRepository.findByGroupIdAndActivityType(groupId, activityTypeId);
    }

    public Proposition createProposition(PropositionCreateRequest propositionCreateRequest) {

        Proposition proposition = Proposition
                .builder()
                .groupId(propositionCreateRequest.getGroupId())
                .activityType(propositionCreateRequest.getActivityType())
                .userId(propositionCreateRequest.getUserId())
                .startDatetime(propositionCreateRequest.getStartDatetime())
                .endDatetime(propositionCreateRequest.getEndDatetime())
                .build();

        return propositionRepository.save(proposition);
    }

    @Getter
    static class PropositionUpdateDetermine {
        private final Proposition proposition;
        private boolean updated = false;

        protected PropositionUpdateDetermine(Proposition oldProposition, PropositionPatchRequest newPropositionPatchRequest) {
            if(oldProposition.getActivityType() != newPropositionPatchRequest.getActivityType()){
                oldProposition.setActivityType(newPropositionPatchRequest.getActivityType());
                updated = true;
            }

            if(!oldProposition.getEndDatetime().equals(newPropositionPatchRequest.getEndDatetime())){
                oldProposition.setEndDatetime(newPropositionPatchRequest.getEndDatetime());
                updated = true;
            }
            if(!oldProposition.getStartDatetime().equals(newPropositionPatchRequest.getStartDatetime())){
                oldProposition.setStartDatetime(newPropositionPatchRequest.getStartDatetime());
                updated = true;
            }

            this.proposition = oldProposition;
        }

    }

    public Proposition patchProposition(UUID propositionId, PropositionPatchRequest propositionPatchRequest) {

        Proposition oldProposition = propositionRepository.findById(propositionId).orElse(null);
        if(oldProposition != null){
            PropositionUpdateDetermine propositionUpdateDetermine = new PropositionUpdateDetermine(oldProposition, propositionPatchRequest);
            if(propositionUpdateDetermine.updated)
                return propositionRepository.save(propositionUpdateDetermine.proposition);
            return propositionUpdateDetermine.proposition;
        }
        return null;
    }

    public void deleteProposition(UUID propositionId) {
        propositionRepository.deleteById(propositionId);
    }



}
