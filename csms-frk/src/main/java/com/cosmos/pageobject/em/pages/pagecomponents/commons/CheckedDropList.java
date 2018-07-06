package com.cosmos.pageobject.em.pages.pagecomponents.commons;

import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.cosmos.pageobject.em.pages.pagecomponents.IWebDriverAware;
import com.cosmos.util.WaitUtils;

import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class CheckedDropList extends HtmlElement
							implements IWebDriverAware {
	
	@FindBy(how = How.XPATH, using = ".//li")
	private List<CheckedListItem> dropListItemsAll;	
	@FindBy(how = How.XPATH, using = ".//li[@class = 'active']")
	@Timeout(0)
	private List<CheckedListItem> dropListItemsChecked;
	@FindBy(how = How.XPATH, using = ".//li[not(@class = 'active')]")
	private List<CheckedListItem> dropListItemsUnchecked;
	private WebDriver driver;
	
	public void checkItemWithText(String text)
	{
		dropListItemsAll	
			.stream()
			.filter((item) -> text.equals(item.getText().trim()))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(String.format("List item with text [%s] seems to be absent in the list", text)))
			.select();				
	}

	public void uncheckAll()
	{			
		int i = 0;
		while (dropListItemsChecked.size() != 0)
		{
			CheckedListItem checkedListItem = dropListItemsChecked.iterator().next();
			checkedListItem.uncheck();
			//WaitUtils.waitUntilElementStaleness(checkedListItem, driver);
			System.out.println(new Date().getTime() + " Iteration " + i++);
		}
		System.out.println(new Date().getTime() + " UNCHECKED ALL ");
	}

	@Override
	public void setWebDriver(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
}
