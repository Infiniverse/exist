/*
 *  eXist Open Source Native XML Database
 *  Copyright (C) 2001-04 The eXist Project
 *  http://exist-db.org
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
package org.exist.memtree;

import org.w3c.dom.CDATASection;
import org.w3c.dom.DOMException;
import org.w3c.dom.Text;

/**
 * Represents a CDATA section.
 * 
 * @author wolf
 */
public class CDATASectionImpl extends NodeImpl implements CDATASection {

    /**
     * @param doc
     * @param nodeNumber
     */
    public CDATASectionImpl(DocumentImpl doc, int nodeNumber) {
        super(doc, nodeNumber);
    }

    /* (non-Javadoc)
     * @see org.w3c.dom.Text#splitText(int)
     */
    public Text splitText(int offset) throws DOMException {
        return null;
    }

    /* (non-Javadoc)
     * @see org.w3c.dom.CharacterData#deleteData(int, int)
     */
    public void deleteData(int offset, int count) throws DOMException {
    }

    /* (non-Javadoc)
     * @see org.w3c.dom.CharacterData#getData()
     */
    public String getData() throws DOMException {
        return new String(document.characters, document.alpha[nodeNumber],
                document.alphaLen[nodeNumber]);
    }

    /* (non-Javadoc)
     * @see org.w3c.dom.CharacterData#substringData(int, int)
     */
    public String substringData(int offset, int count) throws DOMException {
        return null;
    }

    /* (non-Javadoc)
     * @see org.w3c.dom.CharacterData#replaceData(int, int, java.lang.String)
     */
    public void replaceData(int offset, int count, String arg)
            throws DOMException {
    }

    /* (non-Javadoc)
     * @see org.w3c.dom.CharacterData#insertData(int, java.lang.String)
     */
    public void insertData(int offset, String arg) throws DOMException {
    }

    /* (non-Javadoc)
     * @see org.w3c.dom.CharacterData#appendData(java.lang.String)
     */
    public void appendData(String arg) throws DOMException {
    }

    /* (non-Javadoc)
     * @see org.w3c.dom.CharacterData#setData(java.lang.String)
     */
    public void setData(String data) throws DOMException {
    }

}
