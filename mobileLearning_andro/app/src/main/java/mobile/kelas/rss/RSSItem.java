package mobile.kelas.rss;

public class RSSItem {
	
	private String title = null;
	private String description = null;
	private String link = null;
	private String pubDate = null;
	private String category = null;
	
	RSSItem(){
	}
	
	void setTitle(String value)
	{
		title = value;
	}
	void setDescription(String value)
	{
		description = value;
	}
	void setLink(String value)
	{
		link = value;
	}
	void setPubdate(String value)
	{
		pubDate = value;
	}
	void setCategory(String value)
	{
		category = value;
	}
	
	
	public String getTitle()
	{
		return title;
	}
	public String getDescription()
	{
		return description;
	}
	public String getLink()
	{
		return link;
	}
	public String getPubdate()
	{
		return pubDate;
	}
	public String getCategory()
	{
		return category;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		if (title.length() > 100)
		{
			return title.substring(0, 100) + "...";
		}
		return title;
	}
	
	public String toString1(){
		if (description.length() > 100)
		{
			return description.substring(0, 100);
		}
		return description;
	}
	

}


