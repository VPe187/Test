Feature: Event page events

	Background:
		Given the 'Event' page is opened

	Scenario: Event page should show 56 upcoming events
		Then I can see 56 upcoming event cards
