#dir2html

A simple java library to generate html from directory structures

##Example

###Directory Structure
    ├── JAXRSConfiguration.java
    ├── registrations
    │   ├── boundary
    │   │   ├── Registrations.java
    │   │   └── RegistrationsResource.java
    │   ├── control
    │   │   └── VatCalculator.java
    │   └── entity
    │       └── Registration.java
    └── tracing
        └── boundary
            └── LoggerExposer.java

###Example usage

    DirToHtml.toHtml(new File("/test/base/dir"), "/registrations/control/VatCalculator.java", "https://example.com/some/random/prefix?file={file}")

###Generated HTML
    <ul class="directory-tree">
      <li class="directory">
        <ul class="directory">
          <li class="directory-name">/</li>
          <li class="directory">
            <ul class="directory">
              <li class="directory-name">registrations</li>
              <li class="directory">
                <ul class="directory">
                  <li class="directory-name">boundary</li>
                  <li class="file java" data-path="/registrations/boundary/Registrations.java"><a href="https://example.com/some/random/prefix?file=%2Fregistrations%2Fboundary%2FRegistrations.java">Registrations.java</a></li>
                  <li class="file java" data-path="/registrations/boundary/RegistrationsResource.java"><a href="https://example.com/some/random/prefix?file=%2Fregistrations%2Fboundary%2FRegistrationsResource.java">RegistrationsResource.java</a></li>
                </ul>
              </li>
              <li class="directory">
                <ul class="directory">
                  <li class="directory-name">control</li>
                  <li class="file java active" data-path="/registrations/control/VatCalculator.java"><a href="https://example.com/some/random/prefix?file=%2Fregistrations%2Fcontrol%2FVatCalculator.java">VatCalculator.java</a></li>
                </ul>
              </li>
              <li class="directory">
                <ul class="directory">
                  <li class="directory-name">entity</li>
                  <li class="file java" data-path="/registrations/entity/Registration.java"><a href="https://example.com/some/random/prefix?file=%2Fregistrations%2Fentity%2FRegistration.java">Registration.java</a></li>
                </ul>
              </li>
            </ul>
          </li>
          <li class="directory">
            <ul class="directory">
              <li class="directory-name">tracing</li>
              <li class="directory">
                <ul class="directory">
                  <li class="directory-name">boundary</li>
                  <li class="file java" data-path="/tracing/boundary/LoggerExposer.java"><a href="https://example.com/some/random/prefix?file=%2Ftracing%2Fboundary%2FLoggerExposer.java">LoggerExposer.java</a></li>
                </ul>
              </li>
            </ul>
          </li>
          <li class="file java" data-path="/JAXRSConfiguration.java"><a href="https://example.com/some/random/prefix?file=%2FJAXRSConfiguration.java">JAXRSConfiguration.java</a></li>
        </ul>
      </li>
    </ul>


Apply some css and you get:

![screenshot 1](https://raw.githubusercontent.com/sne11ius/dir2html/master/screenshot.png)

