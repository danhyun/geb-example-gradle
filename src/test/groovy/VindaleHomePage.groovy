import geb.Page

/**
 * Created by root on 2/1/14.
 */
class VindaleHomePage extends Page {

    static at = { title == "Paid Online Surveys - Get Paid to Take Surveys - Vindale Research" }
    static content = {

        splash {
            $('div#splash')
        }

        beHeard {
            splash.find('h1')
        }

        form {
            $('form[name="surveyForm"]')
        }

    genders {
        form.find('input[name="gender"]')
    }

    yearOfBirth {
        form.find('input[name="yearOfBirth"]')
    }

    postalCode {
        form.find('input[name="addressZip"]')
    }

    submitButton {
        form.find('input[type="image"]')
    }

    privacyPolicy {
        module FooterModule
    }
}

}
