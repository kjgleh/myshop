package me.kjgleh.myshop.study

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class CreateStudiesService(
    private val studyRepository: StudyRepository
) {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun create(index: Int) {
        if (index == 2) throw IllegalStateException("wow")
        studyRepository.save(Study(name = UUID.randomUUID().toString()))
    }
}
