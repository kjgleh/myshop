package me.kjgleh.myshop.study

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class StudyController(
    private val studyService: StudyService
) {

    @PostMapping("/api/study")
    fun create(@RequestBody studyDto: StudyDto): ResponseEntity<Unit> {
        studyService.create(studyDto)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping("/api/study/{id}")
    fun get(@PathVariable id: Long): ResponseEntity<StudyDto> {
        return ResponseEntity.status(HttpStatus.OK).body(studyService.get(id))
    }
}
