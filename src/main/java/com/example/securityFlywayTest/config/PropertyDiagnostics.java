package com.example.securityFlywayTest.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/*起動時自動的に走って各種クラスや設定値がとれてるか確認できる 
 * 便利なので置いておく
*/
@Component
public class PropertyDiagnostics implements ApplicationListener<ApplicationReadyEvent> {

    private final Environment environment;

    @Value("${snowflake.epoch:NOT_FOUND}")
    private String epochValue;

    public PropertyDiagnostics(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // 1. 有効プロファイル出力
        String[] activeProfiles = environment.getActiveProfiles();
        System.out.println("=== Property Diagnostics ===");
        System.out.println("Active Profiles: " + (activeProfiles.length == 0 ? "(none)" : Arrays.toString(activeProfiles)));

        // 2. 値を Environment から取得
        String prop = environment.getProperty("snowflake.epoch");
        System.out.println("Environment.getProperty(\"snowflake.epoch\") = " + prop);

        // 3. @Value での取得
        System.out.println("@Value snowflake.epoch = " + epochValue);

        // 4. デフォルトプロファイルの確認
        String[] defaultProfiles = environment.getDefaultProfiles();
        System.out.println("Default Profiles: " + Arrays.toString(defaultProfiles));

        // 5. 注意ポイント
        if (prop == null || "NOT_FOUND".equals(epochValue)) {
            System.out.println("⚠ snowflake.epoch が読み込まれていません。");
            System.out.println("  - application.properties の場所を確認してください（src/main/resources）");
            System.out.println("  - プロファイル設定（spring.profiles.active）が正しいか確認してください");
            System.out.println("  - 本番とテストで設定ファイルの場所が違わないか確認してください");
        }

        System.out.println("============================");
    }
}
