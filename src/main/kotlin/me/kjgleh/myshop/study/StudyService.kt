package me.kjgleh.myshop.study

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StudyService(
    private val studyRepository: StudyRepository,
    private val createStudiesService: CreateStudiesService
) {

    @Transactional
    fun create(studyDto: StudyDto) {
        studyRepository.save(Study(name = studyDto.name))
    }

    @Transactional(readOnly = true)
    fun get(id: Long): StudyDto {
        val study = studyRepository.findById(id).orElseThrow {
            IllegalArgumentException()
        }
        return StudyDto(
            name = study.name
        )
    }

    @Transactional
    fun createStudies() {
//        studyRepository.save(Study(name = "test"))
        repeat(3) {
            try {
                createStudiesService.create(it)
            } catch (e: Exception) {
                println("index:$it exception")
            }
        }

        throw IllegalStateException("test exception")
    }
}
