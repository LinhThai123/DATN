package fithou.edu.vn.DoAnTotNghiep.promotion.entity;

public enum PromotionType {
    PERCENTAGE ("Phần trăm"),
    FIXED_AMOUNT ("Tiền mặt");

    public final String label;

    private PromotionType(String label) {
        this.label = label;
    }
}
