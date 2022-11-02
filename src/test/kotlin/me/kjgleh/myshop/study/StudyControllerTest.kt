package me.kjgleh.myshop.study

import me.kjgleh.myshop.Support.fixture
import me.kjgleh.myshop.Support.objectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
internal class StudyControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var studyService: StudyService

    @Test
    fun `create study`() {
        // Arrange

        // Act
        val actual = mockMvc.perform(
            post("/api/study")
                .content(objectMapper.writeValueAsString(fixture<StudyDto>()))
                .contentType(MediaType.APPLICATION_JSON)
        )

        // Assert
        actual.andExpect(status().isCreated)
    }

    @Test
    fun `get study`() {
        // Arrange
        val studyDto = fixture<StudyDto>()
        studyService.create(studyDto)

        // Act
        val actual = mockMvc.perform(
            get("/api/study/1").contentType(MediaType.APPLICATION_JSON)
        )

        // Assert
        actual.andExpect(status().isOk)
        val response = actual.andReturn().response.contentAsString
        assertThat(response).isEqualToIgnoringWhitespace(
            objectMapper.writeValueAsString(
                StudyDto(
                    name = studyDto.name
                )
            )
        )
    }
}
