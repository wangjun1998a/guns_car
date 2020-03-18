package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 车辆交易
 * </p>
 *
 * @author Alin
 * @since 2020-03-17
 */
@TableName("vehicle_transaction")
public class Transaction extends Model<Transaction> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 图片
     */
    @TableField("used_img")
    private String usedImg;
    /**
     * 已使用年限
     */
    @TableField("service_life")
    private String serviceLife;
    /**
     * 已行驶公里数
     */
    private String traveled;
    /**
     * 年检证件
     */
    private String inspection;
    /**
     * 行驶证
     */
    @TableField("driving_license")
    private String drivingLicense;
    /**
     * 保险
     */
    private String insurance;
    /**
     * 保养维修记录
     */
    @TableField("maintenance_record")
    private String maintenanceRecord;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsedImg() {
        return usedImg;
    }

    public void setUsedImg(String usedImg) {
        this.usedImg = usedImg;
    }

    public String getServiceLife() {
        return serviceLife;
    }

    public void setServiceLife(String serviceLife) {
        this.serviceLife = serviceLife;
    }

    public String getTraveled() {
        return traveled;
    }

    public void setTraveled(String traveled) {
        this.traveled = traveled;
    }

    public String getInspection() {
        return inspection;
    }

    public void setInspection(String inspection) {
        this.inspection = inspection;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getMaintenanceRecord() {
        return maintenanceRecord;
    }

    public void setMaintenanceRecord(String maintenanceRecord) {
        this.maintenanceRecord = maintenanceRecord;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Transaction{" +
        ", id=" + id +
        ", name=" + name +
        ", usedImg=" + usedImg +
        ", serviceLife=" + serviceLife +
        ", traveled=" + traveled +
        ", inspection=" + inspection +
        ", drivingLicense=" + drivingLicense +
        ", insurance=" + insurance +
        ", maintenanceRecord=" + maintenanceRecord +
        "}";
    }
}
