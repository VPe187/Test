Feature: Search on the videos page

	Background:
		Given the 'Article' page is opened

	Scenario Outline:
		Given the 'Article' page is opened
		When I type '<input>' into the article search field
		Then I see <numberOfCards> article cards
		Examples:
			| input      | numberOfCards |
			| Blockchain | 36			 |
			| Java	     | 31			 |
