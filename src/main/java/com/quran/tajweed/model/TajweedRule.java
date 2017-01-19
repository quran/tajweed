package com.quran.tajweed.model;

import com.quran.tajweed.rule.GhunnaRule;
import com.quran.tajweed.rule.IdghamRule;
import com.quran.tajweed.rule.IkhfaRule;
import com.quran.tajweed.rule.IqlabRule;
import com.quran.tajweed.rule.MaadRule;
import com.quran.tajweed.rule.MeemRule;
import com.quran.tajweed.rule.QalqalahRule;
import com.quran.tajweed.rule.Rule;

import java.util.Arrays;
import java.util.List;

public class TajweedRule {

  public static final List<TajweedRule> MADANI_RULES = Arrays.asList(
      new TajweedRule(new GhunnaRule(false)),
      new TajweedRule(new IdghamRule()),
      new TajweedRule(new IkhfaRule(false)),
      new TajweedRule(new IqlabRule()),
      new TajweedRule(new MeemRule(false)),
      new TajweedRule(new QalqalahRule(false)),
      new TajweedRule(new MaadRule())
  );

  public static final List<TajweedRule> NASKH_RULES = Arrays.asList(
      new TajweedRule(new GhunnaRule(true)),
      new TajweedRule(new IdghamRule()),
      new TajweedRule(new IkhfaRule(true)),
      new TajweedRule(new IqlabRule()),
      new TajweedRule(new MeemRule(true)),
      new TajweedRule(new QalqalahRule(true)),
      new TajweedRule(new MaadRule())
  );

  public final Rule rule;

  public TajweedRule(Rule rule) {
    this.rule = rule;
  }
}
