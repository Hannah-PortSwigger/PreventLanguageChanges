# Prevent Language Changes

Replace body of specific request with `{"lang_code":"en-us"}`

## Usage
1. Load extension into Burp Suite Professional or Commmunity edition
2. Configure session handling rule to "Invoke Burp extension"
3. Configure appropriate scope on session handling rule
4. Test session handling rule in Repeater to confirm it is working appropriately
5. Export session handling rule from Burp and import to Enterprise
    - Export from Burp: "Settings > Sessions > Session handling rules > Cog button > Save settings"
    - [Import to Enterprise](https://portswigger.net/burp/documentation/enterprise/user-guide/working-with-sites/site-settings/scan-configurations/custom-configs#importing-scan-configurations)

## Additional configuration
If you are unable to have enough of a fine-grained control over when a session handling rule is applied, there is some commented code that provides an HTTP handler to function directly on your outgoing traffic.

To use this, remove any session handling configurations you may have, uncomment the code and rebuild the extension using `./gradlew jar`

Reload the extension in your given Burp edition.