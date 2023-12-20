Feature: Search on the videos page

	Background:
		Given the 'Speaker' page is opened

	Scenario Outline:
		Given the 'Speaker' page is opened
		When I type '<input>' into the speaker search field
		Then I see <numberOfCards> speaker cards
		Examples:
			| input       | numberOfCards |
			| Peter		  | 10            |
		    | Ádám        | 1             |
			| Adam        | 19            |