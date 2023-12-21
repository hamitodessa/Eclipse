package OBS_C_2025;

public class CheckListItem {
	 private String[] label = {"",""};
	 
	 
	  public boolean isSelected = false;

	  public CheckListItem(String label ,String name) {
	    this.label[0] = label;
	    this.label[1] = name ;
	  }

	  public boolean isSelected() {
	    return isSelected;
	  }

	  public void setSelected(boolean isSelected) {
	    this.isSelected = isSelected;
	  }

	  @Override
	  public String toString() {
		 return  label[0]  ;
	  }
	  
	  public String surucu(){
			    return label[1];
		}
}
