import java.util.ArrayList; 
import java.util.Iterator; 
import java.util.List; 
import javax.faces.event.ActionEvent;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.view.rich.component.UIXTable;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.binding.OperationBinding; 
import com.oracle.hr.ADFUtil;

public class DeleteAttachments {
    private RichTable searchResultsTable;

    public DeleteAttachments() {
    }

    public void deleteSelectedRows(ActionEvent actionEvent) {
        UIXTable table = getSearchResultsTable();
        Iterator selection = table.getSelectedRowKeys().iterator();
        List rowIndexes = new ArrayList();
        while (selection.hasNext()) {
        Object rowKey = selection.next();
        table.setRowKey(rowKey);
        int index = table.getRowIndex();
        rowIndexes.add(index);
        System.out.println(".... Row row=" + table.getRowData() + "\t" +index);
        }
        DCBindingContainer bindings = (DCBindingContainer)ADFUtil.evaluateEL("#{bindings}");
        OperationBinding opBinding = bindings.getOperationBinding("deleteSelectedRows");
        opBinding.getParamsMap().put("selectedRowIndexes", rowIndexes);
        opBinding.execute();
    }

    public void setSearchResultsTable(RichTable searchResultsTable) {
        this.searchResultsTable = searchResultsTable;
    }

    public RichTable getSearchResultsTable() {
        return searchResultsTable;
    }
}
