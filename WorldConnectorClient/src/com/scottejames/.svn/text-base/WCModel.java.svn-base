package com.scottejames;
import java.util.List;

import com.shineyappythings.restfulservice.Message;

import scott.mvc.Model;
import scott.mvc.gui.table.FTableModel;

public class WCModel extends Model
{
	private WCAppModel _appModel = null;
	private WCTableModel _appTableModel = new WCTableModel(this,
			new WCTableDefinition());

	private List<Message> _messageList = null;
	
	public FTableModel getTableModel()
	{
		_appModel = WCAppModel.getInstance();
		_appModel.addObserver(this);
		
		return _appTableModel;
	}
	
    @Override
    public void dependentModelUpdated(Model m, CONTEXT context)
    {
       
    }

	public void addComments(List<Message> comments)
	{
		_messageList = comments;
	}
	public List<Message> getComments()
	{
		return _messageList;
	}

}

