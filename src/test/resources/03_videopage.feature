Feature: Search on the videos page

	Background:
		Given the 'Video' page is opened

	Scenario Outline:
		Given the 'Video' page is opened
		When I type '<input>' into the video search field
		Then I see <numberOfCards> video cards
		Examples:
			| input       | numberOfCards |
			| Linux		  | 4             |
			| Android     | 14            |
			| Windows     | 2            |

