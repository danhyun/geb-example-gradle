import groovy.time.TimeCategory
import org.openqa.selenium.Keys
import spock.lang.*
import geb.*
import geb.spock.*

class VindaleSpec extends GebReportingSpec {

    @Unroll
    def "Test #genderIndex and year #yearOfBirthValue" () {

        when:
        to VindaleClearPage

        and:
        at VindaleHomePage

        and:
        genders[genderIndex].click()
        yearOfBirth << yearOfBirthValue

        then:
        yearOfBirth.val()
        report "birthday"

        where:
        genderIndex | yearOfBirthValue
        1           | randomValidYear
        0           | randomValidYear
        1           | randomValidYear
        0           | randomValidYear
        1           | randomValidYear
        0           | randomValidYear

    }

    def "Make sure vindale loads and has required fields" () {

        when:
        to VindaleClearPage

        then:
        at VindaleHomePage

        and:
        beHeard.text() == "Be heard!"
        form.size()
        report "Should have landed on vindale"

        when:
        genders[0].click()
        report "Clicking on a gender"

        yearOfBirth = randomValidYear
        report "random year"

        assert withAlert {
            submitButton.click()
            report "confirm alert"
        } == "Please complete the following fields:\n\n - Zip/Postal Code\n"

        then:
        privacyPolicy.text contains "We will be updating our Privacy Policy on March 1, 2014."
        waitFor {
            at VindaleHomePage
        }

    }

    private getRandomValidYear() {
        def today = new Date()
        use(TimeCategory) {

            def eighteenYearsAgo = today - 18.years
            def oneHundredYearsAgo = today - 100.years

            ((eighteenYearsAgo..oneHundredYearsAgo).toList().sort{Math.random()}[0]).format("yyyy")

        }
    }

}