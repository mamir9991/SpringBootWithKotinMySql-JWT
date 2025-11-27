package com.example.NoteDemo.SpringBootWithKotin.services.onboarding

import com.example.NoteDemo.SpringBootWithKotin.dataSource.onboarding.OnboardingRepository
import org.springframework.stereotype.Service

@Service
class OnboardingService(private val onboardingRepository: OnboardingRepository) {

    fun getOnboardingData() = onboardingRepository.getOnboardingList()

}