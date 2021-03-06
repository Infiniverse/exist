xquery version "1.0";

import module namespace request="http://exist-db.org/xquery/request";
import module namespace xdb = "http://exist-db.org/xquery/xmldb";

if ($exist:path eq '') then
   <dispatch xmlns="http://exist.sourceforge.net/NS/exist">
     <redirect url="{concat(request:get-uri(), '/')}"/>
   </dispatch>
else if ($exist:path eq "/") then
    <dispatch xmlns="http://exist.sourceforge.net/NS/exist">
        <redirect url="admin.xql"/>
    </dispatch>
else if ($exist:resource eq "admin.xql") then
    let $panel := request:get-parameter("panel", ())
    return
        <dispatch xmlns="http://exist.sourceforge.net/NS/exist">
        {
            if ($panel eq 'install') then
                <view>
                    <forward servlet="XSLTServlet">
                        (: Apply xsltforms.xsl stylesheet :)
                		<set-attribute name="xslt.stylesheet"
                			value="{$exist:root}/xforms/xsltforms/xsltforms.xsl"/>
                	    <set-attribute name="xslt.output.omit-xml-declaration" value="yes"/>
                	    <set-attribute name="xslt.output.indent" value="no"/>
                	    <set-attribute name="xslt.output.media-type" value="text/html"/>
                	    <set-attribute name="xslt.output.method" value="xhtml"/>
                	    <set-attribute name="xslt.baseuri" value="../xforms/xsltforms/"/>
                	</forward>
            	</view>
            else
                ()
        }
        </dispatch>

else if (contains($exist:path, "$shared/")) then
    <dispatch xmlns="http://exist.sourceforge.net/NS/exist">
        <forward url="/rest/apps/shared-resources/{substring-after($exist:path, '/$shared/')}"/>
    </dispatch>
    
else
    <ignore xmlns="http://exist.sourceforge.net/NS/exist">
        <cache-control cache="yes"/>
    </ignore>