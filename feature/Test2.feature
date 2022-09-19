Feature: Goosehead DA Smoke Test

  @TC_GH_01 @responsePage @valid @home 
  Scenario: Verify the House only insurance search flow with valid data
    Given Homepage is loaded
    When Select the address "1125 North Charles Street, Baltimore, MD, USA" from Google API suggestions in textbox with label "Let us know your home address"
    And Click on button with title "Let’s Do This"
    And Click on button with title "Yes"
    And Click on button with title "House"
    And Select the address "1125 North Charles Street, Baltimore, MD, USA" from Google API suggestions in textbox with label "Address"
    And Click on button with title "Continue"
    And Enter house details Square Footage, Number of Stories, Year Built, Exterior Walls, Roof Materials, Roof Replaced as "200","3","2018","Brick or Stone","Tile","2018" respectively
    And Click on button with title "Continue"
    And Enter personal details FirstName, LastName, Suffix, Birthdate, Gender, MaritalStatus as "Sam","Doe","","02051986","Female","Married" respectively
    And Enter spouse details FirstName, LastName, Suffix, Birthdate, Gender as "Julia","Doe","Jr","08101990","Female" respectively
    And Click on button with title "Continue"
    And Click on button with title "Continue"
    And Add Vehicle details VINNumber as "5XXGT4L38GG043349" and click Find my vehicle button and confirm
    And Click on button with title "Continue"
    And Enter value "deep@gmail.com" in the textbox with label "Email Address"
    And Enter value "8908908900" in the textbox with label "Mobile Number"
    And Click on "See My Rates" and wait for response page to Load
    Then Check if text "Home" is present on the screen

  @TC_GH_02 @onlyAuto @responsePage @valid
  Scenario: Verify Vehicle Insurance only search flow with valid data  
    Given Homepage is loaded
    When Select the address "11850 Freedom Dr, Reston, VA 20190, USA" from Google API suggestions in textbox with label "Let us know your home address"
    And Click on button with title "Let’s Do This"
    And Click on link with title "only interested in auto insurance"
    And Enter personal details FirstName, LastName, Suffix, Birthdate, Gender, MaritalStatus as "Sam","Doe","","02051986","Female","Divorced" respectively
    And Click on button with title "Continue"
    And Click on button with title "Continue"
    And Add Vehicle details VINNumber as "5XXGT4L38GG043349" and click Find my vehicle button and confirm
    And Click on button with title "Continue"
    And Enter value "deep@gmail.com" in the textbox with label "Email Address"
    And Enter value "8908908900" in the textbox with label "Mobile Number"
    And Click on "See My Rates" and wait for response page to Load
    Then Check if text "Auto" is present on the screen

  @TC_GH_03 @responsePage @valid @condo 
  Scenario: Verify the User can select house type as Condo and property info page requires condo details
    Given Homepage is loaded
    When Select the address "1125 North Charles Street, Baltimore, MD, USA" from Google API suggestions in textbox with label "Let us know your home address"
    And Click on button with title "Let’s Do This"
    And Click on button with title "Yes"
    And Click on button with title "Condo"
    And Select the address "1125 North Charles Street, Baltimore, MD, USA" from Google API suggestions in textbox with label "Address"
    And Click on button with title "Continue"
    And Enter condo details Square Footage, Number of Stories, Year Built, Exterior Walls as "8000","3","1998","Stucco" respectively
    And Click on button with title "Continue"
    And Enter personal details FirstName, LastName, Suffix, Birthdate, Gender, MaritalStatus as "Sam","Doe","","02051986","Female","Married" respectively
    And Enter spouse details FirstName, LastName, Suffix, Birthdate, Gender as "Julia","Doe","Jr","08101990","Female" respectively
    And Click on button with title "Continue"
    And Click on button with title "Continue"
    And Add Vehicle details VINNumber as "5XXGT4L38GG043349" and click Find my vehicle button and confirm
    And Click on button with title "Continue"
    And Enter value "deep@gmail.com" in the textbox with label "Email Address"
    And Enter value "8908908900" in the textbox with label "Mobile Number"
    And Click on "See My Rates" and wait for response page to Load
    Then Check if text "Condo" is present on the screen

  @TC_GH_04 @manualAddr
  Scenario: Verify Manual address page is displayed when address is not passed in search
    Given Homepage is loaded
    When Enter value "" in the textbox with label "Let us know your home address"
    And Click on button with title "Let’s Do This"
    Then Check if text "We were unable to find your address." is present on the screen
    And Enter Manual address details address,unit,city,state,zip as "12 GreenField Ave","3","Dallas","TX","75001" respectively
    And Click on button with title "Continue"
    Then Check if text "Are you in the process of purchasing this home?" is present on the screen

  @TC_GH_05 @error @date_Validation @house
  Scenario: Verify the error message when user enters future date for in property info
    Given Homepage is loaded
    When Select the address "1125 North Charles Street, Baltimore, MD, USA" from Google API suggestions in textbox with label "Let us know your home address"
    And Click on button with title "Let’s Do This"
    And Click on button with title "Yes"
    And Click on button with title "House"
    And Select the address "1125 North Charles Street, Baltimore, MD, USA" from Google API suggestions in textbox with label "Address"
    And Click on button with title "Continue"
    And Enter house details Square Footage, Number of Stories, Year Built, Exterior Walls, Roof Materials, Roof Replaced as "200","3","2023","Brick or Stone","Tile","2023" respectively
    And Click on button with title "Continue"
    Then Check if text "Year built cannot be in the future" is present on the screen

  @TC_GH_06 @rent @onlyRenter @responsePage
  Scenario: Verify the Rent House only insurance search flow with valid data
    Given Homepage is loaded
    When Select the address "1125 North Charles Street, Baltimore, MD, USA" from Google API suggestions in textbox with label "Let us know your home address"
    And Click on button with title "Let’s Do This"
    And Click on button with title "No"
    And Click on button with title "Rent"
    And Enter personal details FirstName, LastName, Suffix, Birthdate, Gender, MaritalStatus as "Sam","Doe","Jr","11231994","Female","Divorced" respectively
    And Click on button with title "Continue"
    And Click on link with title "only interested in renters insurance"
    And Enter value "deep@gmail.com" in the textbox with label "Email Address"
    And Enter value "8908908900" in the textbox with label "Mobile Number"
    And Click on "See My Rates" and wait for response page to Load
    Then Check if text "Renters" is present on the screen
    
    
  @TC_GH_07 @married @house 
  Scenario: Verify the user should able to provide spouse details for married marital status
    Given Homepage is loaded
    When Select the address "1125 North Charles Street, Baltimore, MD, USA" from Google API suggestions in textbox with label "Let us know your home address"
    And Click on button with title "Let’s Do This"
    And Click on button with title "Yes"
    And Click on button with title "House"
    And Select the address "1125 North Charles Street, Baltimore, MD, USA" from Google API suggestions in textbox with label "Address"
    And Click on button with title "Continue"
    And Enter house details Square Footage, Number of Stories, Year Built, Exterior Walls, Roof Materials, Roof Replaced as "200","3","2018","Brick or Stone","Tile","2018" respectively
    And Click on button with title "Continue"
    And Enter personal details FirstName, LastName, Suffix, Birthdate, Gender, MaritalStatus as "Sam","Doe","","02051986","Female","Married" respectively
    And Enter spouse details FirstName, LastName, Suffix, Birthdate, Gender as "Julia","Doe","Jr","08101990","Female" respectively
    And Click on button with title "Continue"
    Then Check if text "Which drivers do you want on your policy?" is present on the screen

  @TC_GH_08 @higherValue @responsePage 
  Scenario: Verify the response page for higher square footage area for home
    Given Homepage is loaded
    When Select the address "1125 North Charles Street, Baltimore, MD, USA" from Google API suggestions in textbox with label "Let us know your home address"
    And Click on button with title "Let’s Do This"
    And Click on button with title "Yes"
    And Click on button with title "House"
    And Select the address "1125 North Charles Street, Baltimore, MD, USA" from Google API suggestions in textbox with label "Address"
    And Click on button with title "Continue"
    And Enter house details Square Footage, Number of Stories, Year Built, Exterior Walls, Roof Materials, Roof Replaced as "12000","3","2018","Brick or Stone","Tile","2018" respectively
    And Click on button with title "Continue"
    And Enter personal details FirstName, LastName, Suffix, Birthdate, Gender, MaritalStatus as "Sam","Doe","","02051986","Female","Married" respectively
    And Enter spouse details FirstName, LastName, Suffix, Birthdate, Gender as "Julia","Doe","Jr","08101990","Female" respectively
    And Click on button with title "Continue"
    And Click on button with title "Continue"
    And Add Vehicle details VINNumber as "5XXGT4L38GG043349" and click Find my vehicle button and confirm
    And Click on button with title "Continue"
    And Enter value "deep@gmail.com" in the textbox with label "Email Address"
    And Enter value "8908908900" in the textbox with label "Mobile Number"
    And Click on button with title "See My Rates" 
    Then Check if text "Good news! You qualify for a few exclusive insurance companies who specialize in higher value homes." is present on the screen

  @TC_GH_09 @error @name_Validation @house
  Scenario: Verify the error message when user does not enter firstName in about you page
    Given Homepage is loaded
    When Select the address "1125 North Charles Street, Baltimore, MD, USA" from Google API suggestions in textbox with label "Let us know your home address"
    And Click on button with title "Let’s Do This"
    And Click on button with title "Yes"
    And Click on button with title "House"
    And Select the address "1125 North Charles Street, Baltimore, MD, USA" from Google API suggestions in textbox with label "Address"
    And Click on button with title "Continue"
    And Enter house details Square Footage, Number of Stories, Year Built, Exterior Walls, Roof Materials, Roof Replaced as "200","3","2018","Brick or Stone","Tile","2018" respectively
    And Click on button with title "Continue"
    And Enter personal details FirstName, LastName, Suffix, Birthdate, Gender, MaritalStatus as "","Doe","Jr","02051986","Female","Widowed" respectively
    And Click on button with title "Continue"
    Then Check if text "Please update First Name" is present on the screen
    
  @TC_GH_10 @error @sqFoot_Validation @house
  Scenario: Verify the error message when user does not enter square Footage in property info
   Given Homepage is loaded
    When Select the address "1125 North Charles Street, Baltimore, MD, USA" from Google API suggestions in textbox with label "Let us know your home address"
    And Click on button with title "Let’s Do This"
    And Click on button with title "Yes"
    And Click on button with title "House"
    And Select the address "1125 North Charles Street, Baltimore, MD, USA" from Google API suggestions in textbox with label "Address"
    And Click on button with title "Continue"
    And Enter house details Square Footage, Number of Stories, Year Built, Exterior Walls, Roof Materials, Roof Replaced as "","3","2018","Brick or Stone","Tile","2018" respectively
    And Click on button with title "Continue"
    Then Check if text "Please update square footage" is present on the screen

  @TC_GH_11 @vehicle 
  Scenario: Verify if we select five vehicles, Add vehicle button should be disabled
 		Given Homepage is loaded
    When Select the address "1125 North Charles Street, Baltimore, MD, USA" from Google API suggestions in textbox with label "Let us know your home address"
    And Click on button with title "Let’s Do This"
    And Click on button with title "Yes"
    And Click on button with title "House"
    And Select the address "1125 North Charles Street, Baltimore, MD, USA" from Google API suggestions in textbox with label "Address"
    And Click on button with title "Continue"
    And Enter house details Square Footage, Number of Stories, Year Built, Exterior Walls, Roof Materials, Roof Replaced as "200","3","2018","Brick or Stone","Tile","2018" respectively
    And Click on button with title "Continue"
    And Enter personal details FirstName, LastName, Suffix, Birthdate, Gender, MaritalStatus as "Sam","Doe","","02051986","Female","Married" respectively
    And Enter spouse details FirstName, LastName, Suffix, Birthdate, Gender as "Julia","Doe","Jr","08101990","Female" respectively
    And Click on button with title "Continue"
    And Click on button with title "Continue"
    And Add Vehicle details VINNumber as "5XXGT4L38GG043349" and click Find my vehicle button and confirm
    And Click on button with title "Add Vehicle"
    And Add Vehicle details year, make and Model as "2008", "HONDA" and "CIVIC EX" respectively and click on confirm
    And Click on button with title "Add Vehicle"
    And Add Vehicle details year, make and Model as "2012", "BMW" and "550 I" respectively and click on confirm
    And Click on button with title "Add Vehicle"
    And Add Vehicle details year, make and Model as "2020", "AUDI" and "A3 PREMIUM" respectively and click on confirm
    And Click on button with title "Add Vehicle"
    And Add Vehicle details year, make and Model as "2022", "FORD" and "F650 SUPER DUTY" respectively and click on confirm
    Then Check if the button "Add Vehicle" is "Disabled"

  
