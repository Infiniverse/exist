/*
 *  eXist SecurityManager Module Extension
 *  Copyright (C) 2010 Adam Retter <adam@existsolutions.com>
 *  www.adamretter.co.uk
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 *  $Id$
 */
package org.exist.xquery.functions.securitymanager;

import org.exist.xquery.AbstractInternalModule;
import org.exist.xquery.FunctionDef;

/**
 * eXist Security Manager Module Extension
 *
 * An extension module for interacting with eXist-db Security Manager
 *
 * @author Adam Retter <adam@existsolutions.com>
 *
 * @see org.exist.xquery.AbstractInternalModule#AbstractInternalModule(org.exist.xquery.FunctionDef[])
 */
public class SecurityManagerModule extends AbstractInternalModule {

    public final static String NAMESPACE_URI = "http://exist-db.org/xquery/securitymanager";
    public final static String PREFIX = "sm";
    private final static String RELEASED_IN_VERSION = "eXist-1.5";
    private final static String DESCRIPTION = "Module for interacting with the Security Manager";

    private final static FunctionDef[] functions = {
        new FunctionDef(FindUserFunction.signatures[0], FindUserFunction.class),
        new FunctionDef(FindUserFunction.signatures[1], FindUserFunction.class),
        new FunctionDef(GetAccountMetadataFunction.signatures[0], GetAccountMetadataFunction.class),
        new FunctionDef(GetAccountMetadataFunction.signatures[1], GetAccountMetadataFunction.class)
    };

    public SecurityManagerModule() {
        super(functions);
    }

    @Override
    public String getNamespaceURI() {
        return NAMESPACE_URI;
    }

    @Override
    public String getDefaultPrefix() {
        return PREFIX;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getReleaseVersion() {
        return RELEASED_IN_VERSION;
    }
}