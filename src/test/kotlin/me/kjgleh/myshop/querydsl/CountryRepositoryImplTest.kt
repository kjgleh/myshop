package me.kjgleh.myshop.querydsl

import me.kjgleh.myshop.Support.fixture
import me.kjgleh.myshop.config.QuerydslTestConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import javax.persistence.EntityManager

@DataJpaTest
@Import(QuerydslTestConfiguration::class)
internal class CountryRepositoryImplTest @Autowired constructor(
    private val countryRepository: CountryRepository,
    private val continentRepository: ContinentRepository
) {

    @Autowired
    private lateinit var entityManager: EntityManager

    @Test
    fun `findByName`() {
        // Arrange

        // Act
        val actual = countryRepository.findByName("")

        // Assert
        assertThat(actual).isNull()
    }

    @Test
    @DisplayName("Tuple 확인 테스트")
    fun `findTupleAndContinentByCountryName`() {
        // Arrange
        val continent = fixture<Continent>()
        continentRepository.save(continent)

        val countryName = fixture<String>()
        val country = fixture<Country> {
            property(Country::name) { countryName }
            property(Country::continent) { continent }
        }
        countryRepository.save(country)

        // Act
        val actual =
            countryRepository.findTupleAndContinentByCountryName(
                countryName
            )

        // Assert
        assertThat(actual).isNotNull
    }

    @Test
    @DisplayName("연관관계 시 조인 테스트")
    fun `findByContinent`() {
        // Arrange
        // Continent 1, 2 저장
        val continent1 = fixture<Continent>()
        continentRepository.save(continent1)

        val continent2 = fixture<Continent>()
        continentRepository.save(continent2)

        val countriesWithContinent1 = listOf<Country>(
            fixture {
                property(Country::continent) { continent1 }
            },
            fixture {
                property(Country::continent) { continent1 }
            }
        )

        val countryWithContinent2 = fixture<Country> {
            property(Country::continent) { continent2 }
        }

        // Continent 1, 2에 해당하는 Country 저장
        countryRepository.saveAll(countriesWithContinent1 + countryWithContinent2)

        // Act
        // Continent 1으로 검증
        val actual = countryRepository.findByContinent(continent1.id)

        // Assert
        assertThat(actual.size).isEqualTo(countriesWithContinent1.size)
    }

    @Test
    @DisplayName("연관관계가 없을 때 조인 테스트")
    fun `findByContinent2`() {
        // Arrange
        // Continent 1, 2 저장
        val continent1 = fixture<Continent>()
        continentRepository.save(continent1)

        val continent2 = fixture<Continent>()
        continentRepository.save(continent2)

        val countriesWithContinent1 = listOf<Country>(
            fixture {
                property(Country::continent) { continent1 }
            },
            fixture {
                property(Country::continent) { continent1 }
            }
        )

        val countryWithContinent2 = fixture<Country> {
            property(Country::continent) { continent2 }
        }

        // Continent 1, 2에 해당하는 Country 저장
        countryRepository.saveAll(countriesWithContinent1 + countryWithContinent2)

        // Act
        // Continent 1으로 검증
        val actual = countryRepository.findByContinent2(continent1.id)

        // Assert
        assertThat(actual.size).isEqualTo(countriesWithContinent1.size)
    }

    @Test
    @DisplayName("1:N 관계 조회")
    fun `findByContinent3`() {
        // Arrange
        val continent1 = fixture<Continent>()
        continentRepository.save(continent1)

        val countriesWithContinent1 = listOf<Country>(
            fixture {
                property(Country::continent) { continent1 }
            },
            fixture {
                property(Country::continent) { continent1 }
            },
            fixture {
                property(Country::continent) { continent1 }
            }
        )

        countryRepository.saveAll(countriesWithContinent1)

        entityManager.flush()
        entityManager.clear()

        // Act
        val actual = countryRepository.findByContinent3(continent1.id)

        // Assert
        assertThat(actual).isNotNull
        assertThat(actual?.countries?.size).isEqualTo(3)
    }

    @Test
    @DisplayName("1:N 관계 조회 && fetchjoin")
    fun `findByContinent4`() {
        // Arrange
        val continent1 = fixture<Continent>()
        continentRepository.save(continent1)

        val countriesWithContinent1 = listOf<Country>(
            fixture {
                property(Country::continent) { continent1 }
            },
            fixture {
                property(Country::continent) { continent1 }
            },
            fixture {
                property(Country::continent) { continent1 }
            }
        )

        countryRepository.saveAll(countriesWithContinent1)

        entityManager.flush()
        entityManager.clear()

        // Act
        val actual = countryRepository.findByContinent4(continent1.id)

        // Assert
        assertThat(actual).isNotNull
        assertThat(actual?.countries?.size).isEqualTo(3)
    }

    @Test
    @DisplayName("projection dto반환")
    fun `findCountry1`() {
        // Arrange
        val continent1 = fixture<Continent>()
        continentRepository.save(continent1)

        val country = fixture<Country> {
            property(Country::continent) { continent1 }
        }

        countryRepository.save(country)

        entityManager.flush()
        entityManager.clear()

        // Act
        val actual = countryRepository.findCountry1(country.id)

        // Assert
        assertThat(actual).isEqualTo(
            CountryAndContinentDto(
                countryId = country.id,
                countryName = country.name,
                continentName = continent1.name
            )
        )
    }
}
