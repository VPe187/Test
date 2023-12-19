Feature: Event page events

	Background:
		Given the 'Event' page is opened

	Scenario: Event page should show 56 upcoming events
		Then I can see 53 upcoming event cards
		Then I can set filter to 'Hungary'
		Then I can see 89 events card in Hungary

