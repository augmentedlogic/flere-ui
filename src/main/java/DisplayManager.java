/**
  Copyright (c) 2019 Wolfgang Hauptfleisch <dev@augmentedlogic.com>

  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:

  The above copyright notice and this permission notice shall be included in all
  copies or substantial portions of the Software.

  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
  SOFTWARE.
 **/
package com.augmentedlogic.flere.ui;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.io.IOException;

import java.net.URI;
import java.net.URL;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.*;

import java.io.Writer;
import java.io.StringWriter;

import com.x5.template.Theme;
import com.x5.template.Chunk;



public class DisplayManager
{

    private Theme theme = null;
    private Chunk chunk = null;
    private String themeLayers = "";

    // TODO static method to set property

    public DisplayManager(String templateName)
    {
        Properties systemProperties = System.getProperties();
        String templatePath = systemProperties.getProperty("jettpack.templates.dir");
        this.theme = new Theme(templatePath, this.themeLayers);
        this.chunk = this.theme.makeChunk(templateName);
    }

    // for legacy purposes
    public DisplayManager()
    {

    }


    public static void setTemplateDirProperty(String path)
    {
        if(!path.endsWith(File.separator)) {
            path = path + File.separator;
        }
        Properties props = System.getProperties();
        props.setProperty("jettpack.templates.dir", path);
    }

    public DisplayManager selectTemplate(String templateName)
    {
        Properties systemProperties = System.getProperties();
        String templatePath = systemProperties.getProperty("jettpack.templates.dir");
        this.theme = new Theme(templatePath, this.themeLayers);
        this.chunk = this.theme.makeChunk(templateName);
        return this;
    }

    public DisplayManager set(String key, Object value)
    {
        this.chunk.set(key, value);
        return this;
    }

    public DisplayManager setString(String key, String value)
    {
        this.chunk.set(key, value);
        return this;
    }

    public DisplayManager setInt(String key, int value)
    {
        this.chunk.set(key, value);
        return this;
    }

    @Deprecated
        public void setObject(String key, Object value)
        {
            this.chunk.set(key, value);
        }


    @Deprecated
        public void setArray(String key, String[] value)
        {
            this.chunk.set(key, value);
        }

    @Deprecated
        public void setArrayList(String key, ArrayList<String> value)
        {
            this.chunk.set(key, value);
        }

    @Deprecated
        public void setMap(String key, Map<String,String> value)
        {
            this.chunk.set(key, value);
        }

    @Deprecated
        public void setDeepMap(String key, Map<Integer,Map> value)
        {
            this.chunk.set(key, value);
        }

    public String render() throws Exception
    {
        Writer out = new StringWriter();
        this.chunk.render(out);
        return out.toString();
    }

}
