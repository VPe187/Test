Feature: Search on the community page

	Background:
		Given the 'Community' page is opened

	Scenario Outline:
		Given the 'Community' page is opened
		When I type '<input>' into the search field
		Then I see <numberOfCards> cards
		Examples:
			| input       | numberOfCards |
			| Budapest    | 4             |
			| China       | 1             |
			| Minsk       | 7             |
			| Nyíregyháza | 0             |
			| Croatia	  | 2			  |
