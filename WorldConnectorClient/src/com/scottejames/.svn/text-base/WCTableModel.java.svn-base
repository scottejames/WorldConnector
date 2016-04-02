package com.scottejames;

import java.util.ArrayList;
import java.util.List;

import com.shineyappythings.restfulservice.Message;

import scott.mvc.Model;
import scott.mvc.gui.table.FTableModel;
import scott.mvc.gui.table.FTableRow;

public class WCTableModel extends FTableModel
{

	public WCTableModel(WCModel m, WCTableDefinition t)
	{
		super(m, t);
	}

	@Override
	public void dependentModelUpdated(Model m, CONTEXT context)
	{
		System.out.println("Table model noticed parent model updated");
		List<Message> messageList = ((WCModel) m).getComments();

		List<FTableRow> results = new ArrayList<FTableRow>();
		if (messageList != null)
			for (Message message : messageList)
			{
				results.add(new FTableRow(new String[] { "1",
						message.getUserName(), message.getComment() }, null));

			}
		this.setModelData(results);
	}
}
