
package simulprodata.model.util;


/**
 *
 * @author Klac
 */
class FactoryCellTableRenderer{
    
    static javax.swing.table.DefaultTableCellRenderer createTableCellRenderer(int columna){
        return new RowRendererOfClassOne(columna);
    }
    
    private static class RowRendererOfClassOne extends javax.swing.table.DefaultTableCellRenderer{
        private final int columna;

        RowRendererOfClassOne(int columna) {
            this.columna = columna;
        }
        
        @Override
        public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setBorder(null);
            setBackground(java.awt.Color.WHITE);
       
            setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            if(columna < 0){
                setPreferredSize(new java.awt.Dimension(12, 36));
                setFont(new java.awt.Font("Tahoma", 1, 17));
                setForeground(new java.awt.Color(90,90,90));
                return this;
            }
            if(isSelected){
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            }else{
                setForeground(new java.awt.Color(39,38,39));
            }
            setFont(new java.awt.Font("Arial", 0, 16));
            return this;
        }
    }
}