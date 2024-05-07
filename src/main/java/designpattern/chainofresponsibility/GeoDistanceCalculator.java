package designpattern.chainofresponsibility;


/**
 * @author zhanghaibing
 * @date 2024-03-16
 */

public class GeoDistanceCalculator {
    // 地球平均半径，单位为米
    private static final double EARTH_RADIUS = 6371000.0;

    /**
     * 计算地球上两点之间的球面距离（Haversine公式）
     * @param lat1 第一点的纬度，以弧度表示
     * @param lon1 第一点的经度，以弧度表示
     * @param lat2 第二点的纬度，以弧度表示
     * @param lon2 第二点的经度，以弧度表示
     * @return 返回两点间的距离，单位为米
     */
    public static double calculateDistanceInMeters(double lat1, double lon1, double lat2, double lon2) {
        // Haversine公式计算球面距离
        final double dLat = Math.toRadians(lat2 - lat1);
        final double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return EARTH_RADIUS * c;
    }

    // 提供一个便捷方法，接受的是经纬度的度数形式，并自动转换为弧度
    public static double calculateDistanceFromDegrees(double lat1Degree, double lon1Degree,
                                                      double lat2Degree, double lon2Degree) {
        return calculateDistanceInMeters(Math.toRadians(lat1Degree), Math.toRadians(lon1Degree),
                Math.toRadians(lat2Degree), Math.toRadians(lon2Degree));
    }
}