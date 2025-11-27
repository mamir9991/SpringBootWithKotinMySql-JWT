package com.example.NoteDemo.SpringBootWithKotin.dataSource.onboarding

import com.example.NoteDemo.SpringBootWithKotin.model.Onboarding
import org.springframework.stereotype.Repository


@Repository
interface OnboardingRepository {
    fun getOnboardingList(): List<Onboarding>
}