package com.example.NoteDemo.SpringBootWithKotin.dataSource.onboarding

import com.example.NoteDemo.SpringBootWithKotin.model.Note
import com.example.NoteDemo.SpringBootWithKotin.model.Onboarding
import org.springframework.stereotype.Repository

@Repository
class OnboardingRepoImpl: OnboardingRepository {


    val mockOnboardingList = mutableListOf<Onboarding>(
        Onboarding(title = "Title 1", content = "Content of First Onboarding", imageUrl = "https://images.unsplash.com/photo-1522202176988-66273c2fd55f?w=800", currentStep = "FIRST", isCompleted = false),
        Onboarding(title = "Title 2", content = "Content of Second Onboarding", imageUrl = "https://images.unsplash.com/photo-1522071820081-009f0129c71c?w=800", currentStep = "SECOND", isCompleted = false),
        Onboarding(title = "Title 3", content = "Content of Third Onboarding", imageUrl = "https://images.unsplash.com/photo-1519389950473-47ba0277781c?w=800", currentStep = "THIRD", isCompleted = false),
        Onboarding(title = "Title 4", content = "Content of Forth Onboarding", imageUrl = "https://images.unsplash.com/photo-1512941937669-90a1b58e7e9c?w=800", currentStep = "FOURTH", isCompleted = false),
        Onboarding(title = "Title 5", content = "Content of Fifth Onboarding", imageUrl = "https://picsum.photos/seed/onboarding1/400/300", currentStep = "FINAL", isCompleted = true),
    )


    override fun getOnboardingList(): List<Onboarding> {
        return mockOnboardingList
    }
}