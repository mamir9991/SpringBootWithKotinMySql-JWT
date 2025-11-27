package com.example.NoteDemo.SpringBootWithKotin.controllers.onboarding

import com.example.NoteDemo.SpringBootWithKotin.services.onboarding.OnboardingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/onboarding")
class OnboardingController(private val onboardingService: OnboardingService) {
    @GetMapping
    fun getOnboardingData() = onboardingService.getOnboardingData()
}